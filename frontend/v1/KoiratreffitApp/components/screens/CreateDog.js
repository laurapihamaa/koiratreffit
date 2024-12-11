import React, { useEffect, useState, useCallback } from 'react';
import styles from '../../styles.js';
import { Text, View } from 'react-native';
import DogForm from '../entities/DogForm.js'

const CreateDog = () => {

    return(
    <View>
      <DogForm/>
    </View>
    );
};

export default CreateDog;