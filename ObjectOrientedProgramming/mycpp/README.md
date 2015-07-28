#C++ Assignment

In the case of operator overloads, the use of member functions have been kept to a bare minimum, restricted only to cases which require direct access to the private datamembers, and the remaining operators have been defined as helper functions able to access and operate through the public interface. eg. The operator `+=` has been defined as a member while operator `+` as a helper as the former operates on fewer constraints, and the latter's work can be achieved by invoking the former. Where feasible, functions have been designed to return types facilitating chain calling mechanisms. eg. operators `<<` and `>>` return stream references. In some cases, operators have been overloaded which were not explicitly requested to facilitate the user's syntax in dealing with the class interface.

In particular, the design of the library management system has been greatly condensed by the observation that `books` and `journals`, as well as `students` and `faculty`, may be derived from common interfaces respectively due to their similarities. Further, the management requirements allow us to model both the `paper` and the `people` on similar behaviour with varying properties, enabling a further abstraction to form the `library` interface, from which all entities are derived. These observations have condensed the code for the entire management system to a mere 300 lines, incluing support of file operations.

**Instructions**:

1. **Compiling**: eg. `$g++ -std=c++11 -o my01 my01.cpp`
2. **Executing**: eg. `$./my01`

**Notes**:

* Requires g++.
