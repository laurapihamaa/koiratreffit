import React, { useEffect, useState } from "react";
import styles from "../../styles";
import { View, Text } from 'react-native';
import Image from './Image';

const Dog = () => {
    
    const [dog, setDog] = useState(null);


    useEffect(() => {
        const fetchDog = async() => {
            try{
                const response = await fetch('http://localhost:8080/dogs/getRandomDog');
                const data = await response.json();
                setDog(data);
                console.log(dog)
            }catch (error){
                console.log('error fetching dog: ', error)
            }
        }

        fetchDog();
    }, []);

    return(
        <View style={styles.container}>
            <Image imageArray={dog?.imageData}/>
            <Text>{dog?.name} || {dog?.age}</Text>
            <Text>{dog?.breed}</Text>
            <Text>{dog?.description}</Text>          
        </View>
    );
};

export default Dog;