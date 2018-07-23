enhanced-diagnostics-spring-boot-starter
===

Drop-in starter for enhanced-diagnostics starter

## Add to your depencies
```xml
<dependency>
    <groupId>com.github.timo-reymann</groupId>
    <artifactId>enhanced-diagnostics-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage
Drop in the dependency, and override the default bean, and there you go!

```java
@Bean 
public ReportProcessorBean() {
    return new ReportProcessorBean() {
        @Override
        public void process(Report report) {
            // Process your report, as you like
        }
    };
}
```

On your frontend you can get the public key via ``/report/publicKey`` in pem format.

## Frontend example
```html
<script src="https://unpkg.com/enhanced-diagnostics@1.1.0/dist/main.js"></script>
<script>
    const logger = new DatabaseLogger('db', 1);
    const reporter = new LogReporter(logger);
    
    console.log("This will be transmitted");

    fetch("/report/publicKey", {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
    .then(r => r.json()) // Parse json
    .then(data => { // Get public key
        const key = data.publicKey
        reporter.buildReportChunks(key).then(chunks => { // Submit report
            fetch("/report", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: chunks
            })
        })
    });
</script>
```

## Customize
```yaml 
enhanced-diagnostics:
    route-prefix: /customReportPath
```