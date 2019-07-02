# sin-rm Relationship Manager (Note: All the below command are fully tested and work well on macOS High Sierra version: 10.13.3)
## API Design 
1. please refer to [sin-rest-api.yml](https://github.com/AllenAi007/sin-rm/blob/master/sin-rest-api.yml)

## Database Design
1. client table
`create table client (
    id int(32) not null primary key,
    name varchar(32),
    risk int(2)
)`
2. product table 
`create table product (
    id int(32) not null primary key,
    name varchar(32),
    risk int(2), 
    investment int(2)
)`

## To Run
1. git clone https://github.com/AllenAi007/sin-rm.git
2. cd sin-rm
3. ./mvnw spring-boot:run

## To Test 
By default, we are using h2 in memory database, each of time when start application, it will run schema-h2.sql and data-h2.sql under src/main/resource to create tables and insert some testing data. 
1. Verify by using curl(or copy the url into any browser): 
    1) curl http://localhost:8080/clients/1/recommendations
    sample response: `{"id":1,"name":"Allen","risk":3,"recommendations":[{"id":1,"name":"SG BONDS","risk":2,"investment":2},{"id":2,"name":"CHINA BONDS","risk":3,"investment":4}]}`
    2) curl http://localhost:8080/clients/recommendations
    sample response: `[{"id":3,"name":"HUA","risk":5,"recommendations":[{"id":1,"name":"SG BONDS","risk":2,"investment":2},{"id":2,"name":"CHINA BONDS","risk":3,"investment":4},{"id":3,"name":"THAILAND BONDS","risk":5,"investment":4}]},{"id":1,"name":"Allen","risk":3,"recommendations":[{"id":1,"name":"SG BONDS","risk":2,"investment":2},{"id":2,"name":"CHINA BONDS","risk":3,"investment":4}]},{"id":2,"name":"AI","risk":4,"recommendations":[{"id":1,"name":"SG BONDS","risk":2,"investment":2},{"id":2,"name":"CHINA BONDS","risk":3,"investment":4}]}]`

## Unit Test - UT
`**/UT*.java` for unit test cases 
run by: `./mvnw clean test` or `mvn clean test` if you have maven pre installed
## Integration Test - IT
`**/IT*.java` for integration test cases
1. run by: `./mvnw integration-test` 
2. run with IT only `./mvnw integration-test -DskipUTs=true` 
## JaCOCO - Test Coverage - `84%`
1. ./mvnw clean integration-test. Check IT report - target/jacoco-it/index.html
2. ./mvnw clean test. Check UT report - target/jacoco-ut/index.html
## Performance Testing - PT
1. Pre Set UP, Java 8 or later.
2. Download and set up  Apache JMeter 4.0
3. Start application(`mvn spring-boot:run -Dspring-boot.run.profiles=pt`). (Note that the jmeter configuration is pointed to localhost:8080, if we have different server to run pt, the we need to change jmeter config file accordingly)
3. Run Performance testing:
    1. `cd sin-rm`
    2. `rm -rf tagret/jmeter-pt/`
    3. `rm target/jmeter-pt.log`
    4. `mkdir -p tagret/jmeter-pt/`
    5. `$JMETER_HOME/bin/jmeter.sh -n -t src/test/pt/jemeter/sin-rm-pt.jmx -l target/jmeter-pt.log -e -o target/jmeter-pt/` 
    6. Check the output report `target/jmeter-pt/index.html`

## Add heroku support
1. git clone https://github.com/AllenAi007/sin-rm.git
2. cd sin-rm
3. heroku create // create an app on heroku
4. git push heroku master //deploy your code 

https://secure-badlands-88938.herokuapp.com/clients/recommendations
