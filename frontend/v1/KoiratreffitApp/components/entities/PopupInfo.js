import React, {useEffect} from 'react';
import { View, Text, StyleSheet, Modal, TouchableOpacity } from 'react-native';
import styles from "../../styles";
import { TouchableWithoutFeedback } from 'react-native';

const PopupInfo = ({infoText, isVisible, closeButton}) => {

    //close info after 1 seconds time
    useEffect(() => {
        if (isVisible) {
            const timeout = setTimeout(() => {
                closeButton();
            }, 1000);
            return () => clearTimeout(timeout);
        }
    }, [isVisible, closeButton]);


    return (
        <Modal visible={isVisible} transparent={true} animationType='fade'>
            <View style={styles.infoBackground}>
                <Text style={styles.closeButtonText}>{infoText}</Text>
            </View>
        </Modal>

    );





};

export default PopupInfo;