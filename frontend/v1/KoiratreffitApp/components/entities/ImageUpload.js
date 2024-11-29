import { useState } from "react";
import * as ImagePicker from "expo-image-picker";
import * as FileSystem from 'expo-file-system';
import { TouchableOpacity } from "react-native";
import {View, Alert, Text, Image} from 'react-native';
import styles from "../../styles";


const ImageUpload = ({addImageData}) => {

    const [imagePreview, setImagePreview] = useState(null);

    const uploadImage = async() => {
        const {status} = await ImagePicker.requestMediaLibraryPermissionsAsync();

        if (status !== "granted"){
            Alert.alert("Accept camera permissions to upload images");
            return;
        }

        const result = await ImagePicker.launchImageLibraryAsync();

        if (!result.canceled){

            const imageUri = result.assets[0].uri;
            setImagePreview(imageUri);
            const imageBase64 = await FileSystem.readAsStringAsync(imageUri, {
                encoding: FileSystem.EncodingType.Base64
            });

            addImageData(imageBase64);

        }


    };

    return(
        <View style={styles.uploadContainer}>
            {imagePreview ? (
                <View >
                    <Image source={{uri: imagePreview}} style={styles.smallImage}/>
                </View>
            ):(
                <View>
                <Image source={require('../../assets/icon.png')} style={styles.smallImage}/> 
                </View> 
            )}
            <TouchableOpacity onPress={uploadImage} style={styles.uploadButton}>
                <Text style={{color: '#488257', fontWeight:'bold'}}>Choose image</Text>
            </TouchableOpacity>
        </View>

    );

}

export default ImageUpload;