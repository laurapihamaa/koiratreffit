import React from 'react';
import styles from '../../styles.js';
import { Text, View } from 'react-native';
import Dog from '../entities/Dog.js'

const HomeScreen = () => {
    return (
      <View style={styles.container}>
        <Dog/>
      </View>
    );
  };

export default HomeScreen;