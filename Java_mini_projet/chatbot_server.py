from flask import Flask, request, jsonify
from transformers import BlenderbotForConditionalGeneration, BlenderbotTokenizer
import torch

app = Flask(__name__)

# Charger le tokenizer et le modèle
tokenizer = BlenderbotTokenizer.from_pretrained("facebook/blenderbot-400M-distill")
model = BlenderbotForConditionalGeneration.from_pretrained("facebook/blenderbot-400M-distill")

# Fonction pour générer une réponse à partir d'une question
def generate_response(question):
    try:
        # Prétraiter la question
        inputs = tokenizer([question], return_tensors="pt", max_length=512, truncation=True)

        # Générer la réponse
        with torch.no_grad():
            outputs = model.generate(
                input_ids=inputs['input_ids'], 
                attention_mask=inputs['attention_mask'],
                max_length=100, 
                num_beams=5, 
                early_stopping=True,
                do_sample=False
            )

        # Décoder la réponse
        response = tokenizer.batch_decode(outputs, skip_special_tokens=True)[0]

        return response
    except Exception as e:
        print("An error occurred while generating response:", e)
        return "Sorry, I'm unable to generate a response at the moment."

# Route pour répondre aux questions via une API
@app.route('/api/chat', methods=['POST'])
def chat():
    try:
        input_data = request.json
        question = input_data.get('question')

        if not question:
            return jsonify({"error": "Invalid request data. 'question' field is required."}), 400

        # Générer la réponse à la question
        response = generate_response(question)

        return jsonify({"response": response})

    except Exception as e:
        print("An error occurred:", e)
        return jsonify({"error": "Internal Server Error"}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
