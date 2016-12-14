$(document).on("click", ".event-item", function () {
    var eventId = this.querySelector('[name=event-id]').innerHTML;
    alert("Selected event with id=" + eventId + ". Not done yet :p");
    // reloadEvent("https://localhost:8080/pa165/events/" + eventId);
});

function reloadEvent(href) {
    //what to do if no invoice available
    $.ajax({
        url : href,
        method: 'GET'
    }).success(function(data){
        if (data == null) {
            //alert("null");
            $('#event-detail').html('<p>No event to show</p>');
        } else {
        //     //alert("not null");
            $('.main-content').html($(data).find('#event-detail').html());
        }
    })
}


