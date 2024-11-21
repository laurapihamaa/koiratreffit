import React, { useEffect, useState, useCallback } from 'react';
import styles from '../../styles.js';
import { Text, View } from 'react-native';
import Card from '../entities/Card.js'

const HomeScreen = () => {


  //store dog data from API
  const [dog, setDog] = useState(null);

  //fetch dog data from API
  const fetchDog = async() => {
      try{
          const response = await fetch('http://192.168.100.5:8080/dogs/getRandomDog');
          const data = await response.json();
          setDog(data);
      }catch (error){
          console.log('error fetching dog: ', error)
      }
    };
  
    
  //fetch the first dog when the component mounts, run only once (empty array)
  useEffect(() => {
    fetchDog();
  }, [])

  //function for fetching new dog after card swipe 
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