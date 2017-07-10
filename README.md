# Decoder

Sample java code to decrypt AES encrypted token

Instructions: 
- Clone the repo
- Put the decryption key in src/resources/application.conf
- Build the app:
```
mvn package
```
- Run the app, passing the encrypted token/iv string you get from getEncryptedToken as first parameter (put it inside single quotes to ensure proper shell escaping) : 
```
java -jar target/token-decoder-1.0-SNAPSHOT.jar 'rT7UpXjSMfFqK2s9S2ZsZA3hqGp3+gXiVWd48biLXMGP74VbMruHhBKncE+SbYHIHk398M2778EgkN+eJBfFtGdGfwfhBqlfFFU2WSonQmo0mAQ+bzB7Nviu0kpMmGNaDvy6ggUArnhlcn9atrFbGOHz5c02C4UNgY2+puohUg3BvCkqtInqvr71wQ6MUBwthZ0nsO25x5Qoi8vo8W9ilg==:JH+MhcKL14RnT+X9lkNfQA=='
```
