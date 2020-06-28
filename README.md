# react-native-kashier

## Getting started

`$ npm install react-native-kashier --save`

### Mostly automatic installation

`$ react-native link react-native-kashier`

## Usage

```javascript
import Kashier from "react-native-kashierSdk";

// TODO: What to do with the module?
Kashier;
```

Boilerplate was created using 

https://github.com/brodybits/create-react-native-module#readme


To include in package.json

```json
{
  "dependencies": {
      "react-native-kashier":"file://./react-native-kashierSdk-1.0.1.tgz",
      "react-native-kashier":"./react-native-kashierSdk-1.0.1.tgz"
  }
}
```


npx react-native init test_sdk_wrapper


For development
add 
"react-native-kashier":"*" 

to package.json

install wml globally (For syncing folders), npm link doesn't work
https://github.com/wix/wml

wml add /ssd_data/Work/Elements/react-native-kashier/ ~/Desktop/test_sdk_wrapper/node_modules/react-native-kashier
wml start

