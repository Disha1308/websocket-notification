var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({"user" : document.getElementById("sender").value}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/reply', function (greeting) {
            showGreeting(JSON.parse(greeting.body).from,JSON.parse(greeting.body).content);
        });
    });
}


function subscription(button) {
	stompClient.subscribe('/topic/'+button.attr('value'), function (greeting) {
        showGreeting(JSON.parse(greeting.body).from,JSON.parse(greeting.body).content);
    });    
} 


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/question", {}, JSON.stringify({'from': $("#sender").val(), 'content': $("#question").val(), "tags": [{"id":1} ,{"id":2}]}));
    showGreeting("Me",$("#question").val());
        
}

function showGreeting(sender,message) {
    $("#greetings").append("<tr><td>" + sender + "</td><td>"+message+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    

    $( ".subscribe" ).click(function() { subscription($(this)); });
    $( "#send" ).click(function() { sendName(); });
});