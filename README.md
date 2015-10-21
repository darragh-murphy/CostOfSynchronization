# CostOfSynchronization

HOW EXPENSIVE IS SYNCHRONIZATION?

Reducing the cost of synchronization by  reducing lock granularity can dramatically increase the performance  of your code.

synchronized method – In the code below I have marked the entire method as “synchronized” in order to prevent a concurrency issue that could result when multiple threads access a shared resource – in this case the map “cache”.
