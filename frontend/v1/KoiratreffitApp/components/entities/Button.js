import { TouchableWithoutFeedback, Animated } from "react-native"
import React, { useCallback, useRef } from 'react'
import { FontAwesome } from "@expo/vector-icons"

const Button = ({name, color, size, style, addAsFriend}) => {

    const scale = useRef(new Animated.Value(1)).current;

    const animateScale = useCallback((newValue) =>{
            Animated.spring(scale, {
                toValue: newValue,
                friction: 4,
                useNativeDriver: true
            }).start(() => {
                addAsFriend();
            });
    }, [scale])


    return(
        <TouchableWithoutFeedback
            onPressIn={()=>animateScale(0.6)}
            onPressOut={() =>animateScale(1)}
            delayPressIn={0}
            delayPressOut={100}
            >
            <Animated.View style={[style,{ transform:[{scale}]}]}>
                <FontAwesome 
                    name={name}
                    color={color}
                    size={size}/>
            </Animated.View>
        </TouchableWithoutFeedback>
    )
}

export default Button;