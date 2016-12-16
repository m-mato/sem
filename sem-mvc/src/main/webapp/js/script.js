(function($) {

    function format(item) { return item; }

    $(".fetchData").select2({
        placeholder: "Search for sportsman",
        minimumInputLength: 3,
        ajax: {
            url: "http://localhost:8080/pa165/events/autocomplet",
            dataType: 'json',
            quietMillis: 250,
            method: 'POST',
            data: function (params) {
                return {
                    pattern: params.term // search term
                };
            },
            processResults: function (users) {
                var result = users.map(function(a, index) {
                    index++;
                    return {id: index, text: a.name + " " + a.surname + "\n(" + a.email + ")"};
                });
                return { results: result};
            }
        },
        formatSelection: format,
        formatResult: format
    });

    $('#inviteButton').click(function () {
        var user = $(".js-data-example-ajax option:selected").text();
        var email = user.substr(user.indexOf("("));
        email = email.substr(1, email.length-2);
        eventId = $("#show_event-id").val();
        if (email) {
            $.ajax({
                url: "http://localhost:8080/pa165/invite/" + eventId + "/" + email,
                dataType: 'json',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                // data: {
                //     invitation: {
                //         eventId: eventId,
                //         email: email
                //     }
                // }
                // data: {
                //     eventId: eventId.toString(),
                //         email: email
                // }

            })
        }

    });






    $('.event-item').click(function() {
        var eventId = this.querySelector('[name=event-id]').innerHTML;
        alert("Selected event with id=" + eventId + ". Not done yet :p");
        // reloadEvent("https://localhost:8080/pa165/events/" + eventId);
    });
    function reloadEvent(href) {
        //what to do if no invoice available
        $.ajax({
            url: href,
            method: 'GET'
        }).success(function(data){
            if (data == null) {
                //alert("null");
                $('#event-detail').html('<p>No event to show</p>');
            } else {
            //     //alert("not null");
                $('.main-content').html($(data).find('#event-detail').html());
            }
        });
    }

})(jQuery);
