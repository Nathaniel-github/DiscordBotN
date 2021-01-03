import discord
from keras.models import Sequential
import numpy as np
from sklearn.metrics import accuracy_score
from sklearn import model_selection
import pickle
from os import listdir
from os.path import isfile, join
from PIL import Image
from keras.layers import Dense, Conv2D
from keras.layers import Activation, MaxPooling2D, Dropout, Flatten, Reshape
import keras

client = discord.Client()

model = pickle.load(open("FINAL_trained_model3", "rb"))


@client.event
async def on_ready():
    print('Logged in as ' + str(client.user))


@client.event
async def on_message(message):
    if message.author == client.user:
        return
    if message.content.startswith("&jay"):
        await message.channel.send("Bot is working")
    if len(message.attachments) > 0:
        for i in message.attachments:
            if i.url.endswith("png") or i.url.endswith("jpg"):
                await i.save('out.bmp')
                image = Image.open('out.bmp')
                image.resize((32, 32), Image.ANTIALIAS).save('out.bmp')
                image = Image.open('out.bmp')
                certainty = decide_to_delete(image)
                if certainty.startswith("deletion"):
                    await message.delete()
                await message.channel.send("Certainty of " + certainty)


def decide_to_delete(image):
    data = np.asarray(image)
    data = data.flatten('F')
    data = data.astype(float)
    temp = [data]
    pred = model.predict(np.array(temp))
    if pred.argmax(axis=1) == 0:
        return "deletion " + str(pred[0, 0])
    else:
        return "keep " + str(pred[0, 1])


client.run("token") #token should be here but is removed for security purposes
