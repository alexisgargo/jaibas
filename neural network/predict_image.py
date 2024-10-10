from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
import numpy as np
import os
import json

# Set variables

IMAGE_PATH = 'images/image.jpg'
MODEL_PATH = 'model/densenet_model.keras'
OUT_PATH = 'out'

# Function to preprocess a new image for prediction
def preprocess_new_image(img_path):
    img = image.load_img(img_path, target_size=(150, 150))  # Resize to the same size as the training images
    img_array = image.img_to_array(img)  # Convert image to array
    img_array = np.expand_dims(img_array, axis=0)  # Add batch dimension (1, 150, 150, 3)
    img_array /= 255.0  # Normalize to [0, 1]
    return img_array


# load model
model = load_model(MODEL_PATH)
# Preprocess the image
new_image = preprocess_new_image(IMAGE_PATH)
# Make a prediction
prediction = model.predict(new_image)
# output
if not os.path.exists(OUT_PATH):
    os.makedirs(OUT_PATH)

printable_result = f"{prediction[0][0]:.4f}"
print(f"probability that the image contains garbage: {printable_result}")
result = {'result': printable_result}

with open(f"{OUT_PATH}/result.json", "w") as json_file:
    json.dump(result, json_file, ensure_ascii=False, indent=1)