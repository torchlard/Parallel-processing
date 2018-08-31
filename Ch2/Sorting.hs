
import Data.Time.Clock (diffUTCTime, getCurrentTime)
import System.Environment (getArgs)
import System.Random (StdGen, getStdGen, randoms)
import Control.Parallel (par, pseq)

-- single thread version
sort :: (Ord a) => [a] -> [a]
sort (x:xs) = lesser ++ [x] ++ greater
        where lesser = sort [y | y<-xs, y<x]
              greater = sort [y | y<-xs, y>=x]
sort _ = []

force :: [a] -> ()
force xs = go xs `pseq` ()
  where go (_:xs) = go xs
        go [] = 1

-- par: execute in parallel, pseq: sequentialize threads 
parSort :: (Ord a) => [a] -> [a]
-- parSort (x:xs) = force greater `par` (force lesser `pseq` (lesser ++ x:greater))
parSort (x:xs) = (force greater `par` force lesser) `pseq` (lesser ++ x:greater)
        where lesser = parSort [y | y<-xs, y<x]
              greater = parSort [y | y<-xs, y>=x]
parSort _ = []

-- avoid cost of recursive apply par, set parameter d
parSort2 :: (Ord a) => Int -> [a] -> [a]
parSort2 d list@(x:xs)
      | d <= 0 = sort list
      | otherwise = force greater `par` (force lesser `pseq` (lesser ++ x:greater))
            where lesser = parSort2 d' [y | y<-xs, y<x]
                  greater = parSort2 d' [y | y<-xs, y>=x ]
                  d' = d-1
parSort2 _ _ = []                  


randomInts :: Int -> StdGen -> [Int]
randomInts k g = let result = take k (randoms g)
                  in force result `seq` result

testFn = parSort2 8


main :: IO ()
main = do 
      args <- getArgs
      let count | null args = 5000000
                  | otherwise = read (head args)
      input <- randomInts count `fmap` getStdGen
      -- input <- [5000000, 4999999 .. 1]
      putStrLn $ "we have " ++ show (length input) ++ " elements to sort"
      start <- getCurrentTime
      let sorted = testFn input
      putStrLn $ "Sorted all " ++ show (length sorted) ++ " elements."
      end <- getCurrentTime
      putStrLn $ show (end `diffUTCTime` start) ++ " elapsed"

