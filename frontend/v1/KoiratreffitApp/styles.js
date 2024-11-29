import { StyleSheet, Dimensions } from "react-native";

const { width, height } = Dimensions.get('window');
const scale = (size) => (width / 375) * size;

const styles = StyleSheet.create({

    basicCentering: {
      justifyContent: 'center',
      textAlign: 'center',
      margin: scale(5),
      boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)'
    },
    container: {
      flex: 1,
      backgroundColor: '#fff',
    },

    resizedimage: {
      height: '40%',
      margin: scale(5)
  },
  card: {
    width: width-scale(10),
    height: height*0.9,
    backgroundColor: '#F6F6F6',
    display: 'flex',
    alignItems: 'stretch',
  },
  nameTag: {
    height: '5%',
    backgroundColor: '#DBE9D7',
    fontSize: scale(16),
    textTransform: 'uppercase',
    flexDirection: 'row'
  },
  infoTag: {
    height: '8%',
    backgroundColor: '#ADC9AA',
    fontSize: scale(16),
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
    size: scale(24)
  },
  likeButtonBackground: {
    height: scale(50),
    width: scale(50),
    backgroundColor: '#FFFFFF',
    alignItems: 'center',
    justifyContent: 'center',
    borderColor: '#7fc3fe',
    borderWidth: 1,
    borderRadius: scale(40),
    elevation: 5,
    position: 'absolute',
    bottom: scale(20),
    right: scale(20)
  },
  infoBackground: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  closeButtonText: {
    color: '#FFA9A9',
    fontSize: scale(16),
    fontWeight: 'bold',
  },
  formTextContainer: {
    height: '7%',
    margin: scale(5),
    backgroundColor: '#FFFFFF',
    borderWidth: 1,
    borderRadius: scale(10),
  },
  formTextContainerLarge: {
    height: '30%',
    margin: scale(5),
    backgroundColor: '#FFFFFF',
    borderWidth: 1,
    borderRadius: scale(10),
    textAlignVertical: 'top'
  },
  smallImage: {
    width: 100,
    height: 100,
    borderRadius: 100,
  },
  uploadContainer: {
        justifyContent: "center",
        alignItems: "center",
  },
    uploadButton: {
      padding: 2,
    },
  });

export default styles;