import { useState, useEffect, memo } from "react";
import styles from "../../styles";

const Image = memo(({ imageArray }) => {

    const [images, setImages] = useState([]);
    const [index, setIndex] = useState(0);

    useEffect(() =>{
        if (imageArray) {
            setImages(imageArray.split(','));
            setIndex(0); 
            console.log(images);
        }
    }, [imageArray])

    const nextImage = () => {
        if(index<images.length-1){
            setIndex(index+1);
        }     
    }

    const previousImage = () => {
        if(index>0){
            setIndex(index-1);
        }     
    }

    const image = images[index];
    console.log(images.length)

    return (
        <div>
            {image ? (
                <img src={`data:image/jpeg;base64,${image}`} alt="Uploaded" style={styles.resizedimage}/> )
                : (
                <p>No image available.</p>)}
            <p/>
            <button onClick={previousImage} disabled={index === 0}>
                Previous
            </button>
            <button onClick={nextImage} disabled={images.length==0 || index === images.length - 1}>
                Next
            </button>
        </div>
    );

});

export default Image;