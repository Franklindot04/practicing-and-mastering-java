# Docker And CI/CD Solutions

## Exercise 1

Problems:

- Copying the whole repository can include `.git`, local files, and unnecessary content.
- `ENV DB_PASSWORD=real-password` bakes a secret into the image.
- The Dockerfile may use stale build output if tests and packaging are not run first.

A safer outline copies only the built jar and receives secrets at runtime.

## Exercise 2

Common `.dockerignore` entries:

```text
.git
.github
.idea
.vscode
.DS_Store
target
*.class
.env
*.pem
*.key
```

Excluding `.git` keeps history and metadata out of the image build context and can reduce image build size.

## Exercise 3

A simple workflow should:

- Trigger on pull requests and pushes to `learnjava`.
- Check out the repository.
- Set up Java 17 or the repo's chosen version.
- Run `mvn test`.

For multiple backend projects, use a matrix of project directories and run `mvn -f ${{ matrix.project }}/pom.xml test`.
