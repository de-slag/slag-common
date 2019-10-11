# Architecture
## modules
### base
basic functions, that are:
* interfaces, i.e. functional interfaces (i.e. not from services)
* exceptions

framework dependencies so restricted as possible
### core
technical basic functions that are:
* exceed the limitations of *base* or *utils*
* not special enough to be a *function module*
### model
a basic data model with:
* ony technical content
* scope to persist (for data models that are not to persist, this is not necessary)
### utils
helping classes that:
* only have static access
* only provides logic for language api data types
### function modules
functional modules are logics that:
* need special framework dependencies
* provides or collects special technical logic
* are accessible by builders in main package
* 's name start with '-common-function'

## internal dependencies
core -> base, model, utils

utils -> base

*function modules* -> core, utils




    
    
