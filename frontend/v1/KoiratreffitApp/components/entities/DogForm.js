import React, {useState} from 'react';
import {View, ScrollView, TextInput, Button, Alert} from 'react-native';
import { Picker } from '@react-native-picker/picker';
import styles from "../../styles";

const DogForm = () => {

  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [breed, setBreed] = useState("");
  const [description, setDescription] = useState("");
  const [gender, setGender] = useState("");
  const [location, setLocation] = useState("");

  const [nameError, setNameError] = useState("");
  const [genderError, setGenderError] = useState("");
  const [locationError, setLocationError] = useState("");
  const [breedError, setBreedError] = useState("");
  const [ageError, setAgeError] = useState("");

  const submitDogInfo = async() => {
    const dogData = {
      name,
      age,
      breed,
      description,
      gender,
      location
    }

    try{
          const response = await fetch('http://192.168.100.5:8080/dogs/createNewDog', {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(dogData)
          });

          if(!response.ok){
            const data = await response.json();
            setNameError(data.name);
            setGenderError(data.gender);
            setLocationError(data.location);
            setBreedError(data.breed);
            setAgeError(data.age);

            return;
          }

          setNameError("");
          setGenderError("");
          setLocationError("");
          setBreedError("");
          setAgeError("");

          Alert.alert("Dog succesfully submitted!");

      }catch (error){
          console.log('error fetching dog: ', error)
      }
    };

  return (
    <View>
        <TextInput
          placeholder={nameError === "" ? "Name" : nameError}
          placeholderTextColor={nameError ? '#bd4d5b' : 'gray'}
          value={name}
          onChangeText={setName}
          style={styles.formTextContainer} />
        <TextInput
          placeholder={ageError === "" ? "Age" : ageError}
          placeholderTextColor={ageError ? '#bd4d5b' : 'gray'}
          value={age}
          onChangeText={setAge}
          style={styles.formTextContainer} 
        />
        <TextInput
          placeholder={breedError === "" ? "Breed" : breedError}
          placeholderTextColor={breedError ? '#bd4d5b' : 'gray'}
          value={breed}
          onChangeText={setBreed}
          style={styles.formTextContainer} 
        />
        <TextInput
          placeholder="Description"
          value={description}
          onChangeText={setDescription}
          style={styles.formTextContainerLarge} 
        />
        <TextInput
          placeholder={locationError === "" ? "Location" : locationError}
          placeholderTextColor={locationError ? '#bd4d5b' : 'gray'}
          value={location}
          onChangeText={setLocation}
          style={styles.formTextContainer} 
        />
        <Picker 
          selectedValue={gender}
          onValueChange={(itemValue) => setGender(itemValue)}
          mode={"dialog"}>
            <Picker.Item label="Select gender" value="" style={{color: genderError ? '#bd4d5b' : 'gray'}} />
            <Picker.Item label="Male" value="male" />
            <Picker.Item label="Female" value="female" />
        </Picker>
        <Button title="Submit" onPress={submitDogInfo} color='#7e9c7f'/>
      </View>
  );
};

export default DogForm;