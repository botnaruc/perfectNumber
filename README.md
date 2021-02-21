# Perfect Number
Perfect Number Spring Boot project based on formulas described here https://en.wikipedia.org/wiki/Perfect_number

Examples:
GET
http://localhost:8080/api/perfect-numbers/verify/6
http://localhost:8080/api/perfect-numbers/verify/6?formula=sum-of-divisors
http://localhost:8080/api/perfect-numbers/verify/6?formula=prime-number

GET
http://localhost:8080/api/perfect-numbers/range?start=5&end=1000
http://localhost:8080/api/perfect-numbers/range?start=5&end=1000&formula=sum-of-divisors
http://localhost:8080/api/perfect-numbers/range?start=5&end=1000&formula=prime-number


