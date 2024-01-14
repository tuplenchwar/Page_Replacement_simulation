# Page_Replacement_simulation

Page Replacement Algorithm Used:
1. FIFO
2. LRU
3. LFU
4. MFU
5. Random pick


**How it works**
● The code creates a sample data of 150 processes and runs all of them against
all the 5 page replacement algorithms. This is repeated 5 times. For each run the
Hit and Miss ratio of that algorithm is stored and after 5 run the average ratio of
each is calculated
● Within each execution cycle of an algorithm, 150 processes are created and
executed in parallel in different threads, 25 threads at a time. Internally, each
process loops up to its ‘size’ times, each time referencing a random page with
the given locality of reference. There is a sleep of 100ms in this class for each
page reference call as suggested by Professor. All of this simulation is present in
the class ProcessExecutor.
● The ProcessExecutor also takes as an argument the type of PagingAlgorithm
and creates an object of class MainMemory containing the object of one of the
actual Page Replacement algorithms (eg: FIFO, etc).
● The logic of loading pages is in class MainMemory, and ideally it should be able
to work with any given Paging Algorithm. Hence, MainMemory only uses an
object of type PagingAlgo which is an interface that contains a contract for what
any Page Replacement algorithm should implement. The object of the actual
implementation (eg: FIFO) is given out by the PagingAlgoFactory.
● Logs are being redirected to the file pageReplacement.log as seen in my
log4j2.xml

**How to run the code**
● In order to run the code, open all the code files in IntelliJ/Eclipse as a Gradle
project. For logging I am using log4j2 which I need to pull-in as a dependency.
For that I have used Gradle.
● The simulation begins in the class PagingTester which has the main method.
You can simply click ‘Run’ on the main() method.
Notes
The program may exhibit different behaviors on various operating systems due to
differences in process management.
