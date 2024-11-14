import React, { useEffect, useState, useCallback } from "react";
import styles from "../../styles";
import { View, Text, Button } from 'react-native';
import Dog from './Dog';

const Card = () => {

    return(
        <View style={[styles.card, styles.basicCentering]}>
            <Dog/>
        </View>   
    );
};

export default Card;