import React, { useEffect, useState, useCallback } from "react";
import styles from "../../styles";
import { View, Text, Image } from 'react-native';

const Dog = () => {
    
    const [dog, setDog] = useState(null);

    const fetchDog = useCallback(async() => {
        try{
            const response = await fetch('http://192.168.100.5:8080/dogs/getRandomDog');
            const data = await response.json();
            setDog(data);
            console.log(dog)
        }catch (error){
            console.log('error fetching dog: ', error)
        }
    });

    useEffect(() => {
        fetchDog();
    }, []);

    return(
        <View>
            <Image src={`data:image/jpeg;base64,${dog?.imageData}`} alt="Uploaded" style={styles.resizedimage}/>
            <View style={[styles.nameTag, styles.basicCentering]}>
                <Text>{dog?.name}</Text>
            </View>
            <View style={[styles.infoTag, styles.basicCentering]}>
                <Text>{dog?.breed} || {dog?.age} || {dog?.location}</Text>
                <Text>{dog?.gender}</Text>
            </View>
            <Text style={[styles.textContainer, styles.basicCentering]}>{dog?.description}</Text>
        </View>   
    );
};

export default Dog;