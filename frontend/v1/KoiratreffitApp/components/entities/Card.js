import React, { Fragment, useCallback, useRef, useEffect } from "react";
import styles from "../../styles";
import { PanResponder, View, Animated } from 'react-native';
import Dog from './Dog';
import Button from './Button';

const Card = ({dog, onSwipe}) => {

    //reference to track cards position
    const swipe=useRef(new Animated.ValueXY()).current;

    //define rotation animation (base on x-axis)
    //swipe distance, rotation angles and prevent rotation value from growing too large
    const rotate = swipe.x.interpolate({
        inputRange: [-1000, 0, 100],
        outputRange:['8deg', '0deg', '-8deg'],
        extrapolate: 'clamp'
    })

    //track users touch on the screen with the pan responder
    const panResponder = PanResponder.create({

        //recognize gesture
        onMoveShouldSetPanResponder: () => true,

        
        //update swipe value according to the touch
        onPanResponderMove: (_, {dx, dy})=>{
            swipe.setValue({x:dx, y:dy});
        },

        //determine action after swipe is released
        onPanResponderRelease: (_, {dx, dy})=>{

            //direction of swipe (left, right)
            const direction = Math.sign(dx); 
            //decide whether to trigger action (based on swipe distance)
            const isActionActive = Math.abs(dx) > 100;

            //if distance is large enough move card off the screen in the direction of the swipe
            //if distance is not large enough, move card to original place
            isActionActive ? 
                Animated.timing(swipe,{
                    duration: 100,
                    toValue:{
                        x: direction * 500,
                        y: dy
                    },
                    useNativeDriver: true //use for smoother animation
                }).start(() => {
                    //trigger onSwipe function and reset position 
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
                    friction: 5 //natural bounce back effect
                }).start()
        }
    })

    //create the animation for card with translation and rotation
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