let messageApi = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages'],
    data: function () {
        return {
            content: ''
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="message content..." v-model="content">' +
        '<input type="button" value="Send" @click="save">' +
        '</div>',
    methods: {
        save: function () {
            let message = {content: this.content};
            messageApi.save({}, message)
                .then(response => response.json()
                    .then(data => this.messages.push(data)));
            this.content = ''
        }
    }
})

Vue.component('message-row', {
    props: ['message'],
    template: '<div><li>{{ message.id }} - {{ message.content }}</li></div>'
});

Vue.component('messages-list', {
    props: ['messages'],
    template: '<div>' +
        '<message-form :messages="messages" />' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" />' +
        '</div>',
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
