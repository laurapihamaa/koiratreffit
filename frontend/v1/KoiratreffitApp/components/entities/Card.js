import React, { Fragment, useCallback, useRef, useEffect } from "react";
import styles from "../../styles";
import { PanResponder, View, Animated } from 'react-native';
import Dog from './Dog';
import Button from './Button';

const Card = ({dog, onSwipe}) => {

    const swipe=useRef(new Animated.ValueXY()).current;

    const rotate = swipe.x.interpolate({
        inputRange: [-1000, 0, 100],
        outputRange:['8deg', '0deg', '-8deg'],
        extrapolate: 'clamp'
    })

    const panResponder = PanResponder.create({
        onMoveShouldSetPanResponder: () => true,

        onPanResponderMove: (_, {dx, dy})=>{
            swipe.setValue({x:dx, y:dy});
        },

        onPanResponderRelease: (_, {dx, dy})=>{
            const direction = Math.sign(dx);
            const isActionActive = Math.abs(dx) > 100;

            isActionActive ? 
                Animated.timing(swipe,{
                    duration: 100,
                    toValue:{
                        x: direction * 500,
                        y: dy
                    },
                    useNativeDriver: true
                }).start(() => {
                    onSwipe().then(() =>{
                        swipe.setValue({ x: 0, y: 0 });
                    });
                    
                }) :
                Animated.spring(swipe, {
                    toValue: {
                        x: 0,
                        y: 0
                    },
                    useNativeDriver: true,
                    friction: 5
                }).start()
        }
    })

    
    const animatedCardStyle = {
        transform: [...swipe.getTranslateTransform(), { rotate }]
    }


    return(
        <Animated.View style={[
            styles.card, 
            styles.basicCentering,
            { transform: swipe.getTranslateTransform()}.transform,
            animatedCardStyle
        ]}
        {...panResponder.panHandlers}>
            <Dog dog={dog}/>
            <Button
                name={styles.likeButton.name}
                color={styles.likeButton.color}
                size={styles.likeButton.size}
                style={styles.likeButtonBackground}/>
        </Animated.View>   
    );
};

export default Card;