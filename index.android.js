/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
    AppRegistry, Text,
} from 'react-native';
MainActivity = require('app/MainActivity');

export default class ReactNativeMaterialToDoList extends Component {
  render() {
      return <Text>
          {'test\n'}
      </Text>
    // return new MainActivity();
  }
}

AppRegistry.registerComponent('ReactNativeMaterialToDoList', () => ReactNativeMaterialToDoList);
