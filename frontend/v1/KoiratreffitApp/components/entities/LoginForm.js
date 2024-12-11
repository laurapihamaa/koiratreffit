import React, {useState} from 'react';
import {View, ScrollView, TextInput, Button, Alert} from 'react-native';
import styles from "../../styles";
import ImageUpload from './ImageUpload';
import { useNavigation } from '@react-navigation/native'; 

const LoginForm = () => {

  const navigation = useNavigation();

  const [userName, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [imageData, setImageData] = useState("");

  const [usernameError, setUsernameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const submitUserInfo = async() => {
    const userData = {
      userName,
      email,
      password,
      imageData
    }

    try{
          const response = await fetch('http://192.168.100.5:8080/register', {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
          });

          if(!response.ok){
            const data = await response.json();

            data.userName && (setUsernameError(data.userName), setUsername(""));
            data.email && (setEmailError(data.email), setEmail(""));
            data.password && (setPasswordError(data.password), setPassword(""));

            return;
          }

          setUsernameError("");
          setEmailError("");
          setPasswordError("");

          Alert.alert("User succesfully submitted!");

          navigation.navigate('Create a profile for your dog');

      }catch (error){
          console.log('error creating user: ', error)
      }
    };

  const addImageData = (imageData) =>{
    setImageData(imageData);
  }

  return (
    <View>
        <ImageUpload addImageData={addImageData}/>
        <TextInput
          placeholder={usernameError === "" ? "Username" : usernameError}
          placeholderTextColor={usernameError ? '#bd4d5b' : 'gray'}
          value={userName}
          onChangeText={setUsername}
          style={styles.formTextContainer} />
        <TextInput
          placeholder={emailError === "" ? "Email" : emailError}
          placeholderTextColor={emailError ? '#bd4d5b' : 'gray'}
          value={email}
          onChangeText={setEmail}
          style={styles.formTextContainer} 
        />
        <TextInput
          placeholder={passwordError === "" ? "Password" : passwordError}
          placeholderTextColor={passwordError ? '#bd4d5b' : 'gray'}
          value={password}
          onChangeText={setPassword}
          style={styles.formTextContainer} 
          secureTextEntry={true} 
        />
        <Button title="Submit" onPress={submitUserInfo} color='#7e9c7f'/>
      </View>
  );
};

export default LoginForm;