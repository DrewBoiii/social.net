let messageApi = Vue.resource('/message{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            content: '',
            id: '',
            uid: '',
            createdAt: ''
        }
    },
    watch: {
        messageAttr: function (newValue, oldValue) {
            this.content = newValue.content;
            this.id = newValue.id;
            this.uid = newValue.uid;
            this.createdAt = newValue.createdAt;
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="message content..." v-model="content">' +
        '<input type="button" value="Send" @click="save">' +
        '</div>',
    methods: {
        save: function () {
            let message = {id: this.id, uid: this.uid, content: this.content, createdAt: this.createdAt};
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
        '<li>{{ message.id }} - Message: {{ message.content }} Posted at: {{ message.createdAt }} </li>' +
        '<span><input type="button" value="Edit" @click="edit" /></span>' +
        '<span><input type="button" value="Remove" @click="remove" /></span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        remove: function () {
            messageApi.remove({id: this.message.id})
                .then(response => {
                    response.ok ? this.messages.splice(this.messages.indexOf(this.message), 1) : [];
                });
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
        '<message-row v-for="message in messages" :key="message.id" :message="message" :editMethod="editMethod" :messages="messages" />' +
        '</div>',
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
    },
    created: function () {
        messageApi.get()
            .then(response => response.json()
                .then(data => data.forEach(message => this.messages.push(message))))
    }
});
