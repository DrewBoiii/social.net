
let messageApi = Vue.resource('/message{/id}');

Vue.component('message-row', {
    props: ['message'],
    template: '<div><li>{{ message.id }} - {{ message.content }}</li></div>'
});

Vue.component('messages-list', {
    props: ['messages'],
    template: '<div><message-row v-for="message in messages" :key="message.id" :message="message" /></div>',
    created: function () {
        messageApi.get()
            .then(response => response.json()
                .then(data => data.forEach(message => this.messages.push(message))))
    }
});

let app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
