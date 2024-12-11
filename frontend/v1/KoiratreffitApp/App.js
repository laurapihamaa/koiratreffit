import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import HomeScreen from './components/screens/HomeScreen';
import CreateDog from './components/screens/CreateDog.js';
import { Button, View, Text } from 'react-native';
import styles from "./styles.js";
import CreateUser from './components/screens/CreateUser.js';

const Stack = createNativeStackNavigator();

const LandingScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <Button title="Go to Home Screen" onPress={() => navigation.navigate('Koirakaverit')} />
      <Button title="Dog form" onPress={() => navigation.navigate('Create a profile for your dog')} />
      <Button title="User form" onPress={() => navigation.navigate('Create a user profile')} />
    </View>
  );
};

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Landing">
        <Stack.Screen name="Landing" component={LandingScreen} />
        <Stack.Screen name="Koirakaverit" component={HomeScreen} />
        <Stack.Screen name="Create a user profile" component={CreateUser} />
        <Stack.Screen name="Create a profile for your dog" component={CreateDog} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}



