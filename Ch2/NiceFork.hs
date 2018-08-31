import Control.concurrent
import Control.Exception (Exception, try)
import qualified Data.Map as M


data ThreadStatus = Running | Finished | Threw Exception deriving (Eq, Show)

-- create new thread manager
newManager :: IO ThreadManager

-- create new managed thread
forkManager :: ThreadManager -> IO () -> IO ThreadId

-- immediately return status of managed thread
getStatus :: ThreadManager -> ThreadId -> IO (Maybe ThreadStatus)

-- block until specific managed thread terminates
waitFor :: ThreadManager -> ThreadId -> IO (Maybe ThreadStatus)

-- block until all managed threads terminate
waitAll :: ThreadManager -> IO ()



