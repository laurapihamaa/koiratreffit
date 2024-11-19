import { StyleSheet, Dimensions } from "react-native";

const { width, height } = Dimensions.get('window');

const styles = StyleSheet.create({

    basicCentering: {
      justifyContent: 'center',
      textAlign: 'center',
      margin: 5,
      boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)'
    },
    container: {
      flex: 1,
      backgroundColor: '#fff',
    },

    resizedimage: {
      height: '40%',
      margin: 5
  },
  card: {
    width: width-10,
    height: height*0.9,
    backgroundColor: '#F6F6F6',
    display: 'flex',
    alignItems: 'stretch',
  },
  nameTag: {
    height: '5%',
    backgroundColor: '#DBE9D7',
    fontSize: 16,
    textTransform: 'uppercase',
    flexDirection: 'row'
  },
  infoTag: {
    height: '8%',
    backgroundColor: '#ADC9AA',
    fontSize: 16,
    textTransform: 'uppercase',
    gap: 2,
    flexDirection: 'column', 
    alignItems: 'center',
  },
  textContainer: {
    height: '40%',
    backgroundColor: '#FFFFFF',
  },
  likeButton: {
    name: 'heart',
    color: '#7fc3fe',
    size: 24
  },
  likeButtonBackground: {
    height: 50,
    width: 50,
    backgroundColor: '#FFFFFF',
    alignItems: 'center',
    justifyContent: 'center',
    borderColor: '#7fc3fe',
    borderWidth: 1,
    borderRadius: 40,
    elevation: 5,
    position: 'absolute',
    bottom: 20,
    right: 20
  }
  });

export default styles;