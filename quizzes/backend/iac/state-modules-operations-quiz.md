# State, Modules, And Operations Quiz

## Multiple Choice

1. Why should `.tfstate` files not be committed?
   - A. They are always empty
   - B. They can contain sensitive or provider-returned values
   - C. Markdown cannot link to them
   - D. They are Java source files

2. When is a module most useful?
   - A. Before the pattern is understood
   - B. When a stable pattern needs clear inputs and outputs
   - C. To hide all security choices
   - D. To avoid plan review

3. What should happen before a real production apply?
   - A. Review the plan and approval path
   - B. Commit `.terraform/`
   - C. Put credentials in Git
   - D. Ignore drift

## Short Answer

4. Explain local state versus remote state at a concept level.

5. Name two reviewer questions for a plan that replaces a database-like resource.

## Design Reading

6. A folder contains `dev`, `staging`, and `prod` configurations. What is one benefit and one risk of this structure?

7. A module outputs a database password for convenience. What is wrong with that design?

