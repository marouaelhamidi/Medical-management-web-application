document.addEventListener('DOMContentLoaded', function () {
    const chatContainer = document.createElement('div');
    chatContainer.style.position = 'fixed';
    chatContainer.style.bottom = '0';
    chatContainer.style.right = '0';
    chatContainer.style.width = '300px';
    chatContainer.style.height = '400px';
    chatContainer.style.border = '1px solid #ccc';
    chatContainer.style.backgroundColor = 'white';
    chatContainer.style.zIndex = '1000';
    chatContainer.style.overflow = 'hidden';
    chatContainer.style.display = 'flex';
    chatContainer.style.flexDirection = 'column';

    const chatHeader = document.createElement('div');
    chatHeader.style.backgroundColor = '#007bff';
    chatHeader.style.color = 'white';
    chatHeader.style.padding = '10px';
    chatHeader.innerText = 'ChatGPT';
    chatContainer.appendChild(chatHeader);

    const chatMessages = document.createElement('div');
    chatMessages.style.flex = '1';
    chatMessages.style.padding = '10px';
    chatMessages.style.overflowY = 'auto';
    chatContainer.appendChild(chatMessages);

    const chatInput = document.createElement('input');
    chatInput.type = 'text';
    chatInput.style.width = '100%';
    chatInput.style.boxSizing = 'border-box';
    chatInput.style.padding = '10px';
    chatInput.placeholder = 'Type your message...';
    chatContainer.appendChild(chatInput);

    document.body.appendChild(chatContainer);

    chatInput.addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
            const message = chatInput.value;
            chatInput.value = '';
            chatMessages.innerHTML += `<div><strong>You:</strong> ${message}</div>`;
            fetch('/api/chat/message', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(message)
            })
                .then(response => response.text())
                .then(data => {
                    chatMessages.innerHTML += `<div><strong>Bot:</strong> ${data}</div>`;
                    chatMessages.scrollTop = chatMessages.scrollHeight;
                });
        }
    });
});
