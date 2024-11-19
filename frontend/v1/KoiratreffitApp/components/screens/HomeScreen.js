import React, { useEffect, useState, useCallback } from 'react';
import styles from '../../styles.js';
import { Text, View } from 'react-native';
import Card from '../entities/Card.js'

const HomeScreen = () => {

  const [dog, setDog] = useState(null);

    const fetchDog = async() => {
        try{
            const response = await fetch('http://192.168.100.5:8080/dogs/getRandomDog');
            const data = await response.json();
            setDog(data);
        }catch (error){
            console.log('error fetching dog: ', error)
        }
    };

    useEffect(() => {
      fetchDog();
    }, [])

    const handleCardSwipe= async() =>{
      await fetchDog();
    };

    return (
      <View>
        <Card dog={dog} onSwipe={handleCardSwipe}/>
      </View>
    );
  };

export default HomeScreen;