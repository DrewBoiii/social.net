let messageApi = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            content: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function (newValue, oldValue) {
            this.content = newValue.content;
            this.id = newValue.id;
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="message content..." v-model="content">' +
        '<input type="button" value="Send" @click="save">' +
        '</div>',
    methods: {
        save: function () {
            let message = {content: this.content};
            if (this.id) {
                messageApi.update({id: this.id}, message)
                    .then(response => response.json()
                        .then(data => {
                            let index = this.messages.map(m => m.id).indexOf(data.id, 0);
                            this.messages.splice(index, 1, data);
                            this.content = '';
                            this.id = '';
                        }));
            } else {
                messageApi.save({}, message)
                    .then(response => response.json()
                        .then(data => this.messages.push(data)));
                this.content = '';
            }
        }
    }
})

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template: '<div>' +
        '<li>{{ message.id }} - {{ message.content }}</li>' +
        '<span><input type="button" value="Edit" @click="edit" /></span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function () {
        return {
            message: null
        }
    },
    template: '<div>' +
        '<message-form :messages="messages" :messageAttr="message"/>' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" :editMethod="editMethod" />' +
        '</div>',
    created: function () {
        messageApi.get()
            .then(response => response.json()
                .then(data => data.forEach(message => this.messages.push(message))))
    },
    methods: {
        editMethod: function (message) {
            this.message = message;
        }
    }
});

let app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
