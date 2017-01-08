(function($) {

    $('.select2').select2();

    $('.input-group.date').datepicker({
        format: 'yyyy/mm/dd',
        language: $('html').attr('lang')
    });

    $('.input-group-addon.email').click(function() {
        var input = $(this).parent().find('input');
        var currentValue = input.val();
        input.val(currentValue + $(this).text());
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
    };

    function format(item) { return item; }

    $(".fetchSportsmans").select2({
        placeholder: "",
        minimumInputLength: 3,
        ajax: {
            url: "http://localhost:8080/pa165/events/autocomplet",
            dataType: 'json',
            quietMillis: 250,
            method: 'POST',
            data: function (params) {
                return {
                    pattern: params.term, // search term
                    event_id: $('[name=inv_event_id]').val()
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

    $('#invite_button').click(function () {
        var user = $(".fetchSportsmans option:selected").text();
        var email = user.substr(user.indexOf("("));
        email = email.substr(1, email.length-2);
        event_id = $("#inv_event_id").val();
        if (email) {
            $.ajax({
                url: "http://localhost:8080/pa165/events/invite",
                dataType: 'json',
                headers: {
                    // 'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                data: JSON.stringify({
                    "event_id": event_id,
                    "inputEmail": email
                }),
                async: false,
                cache: false,
                processData:false,
                success: function () {
                    addMessage("Invitation sent!","", "success");
                },
                error: function () {
                    addMessage("Sorry", "Unexpected error occurred during sending invitation.", "danger");
                }
            })
        }
    });
    $('.select2').click(function () {
        $('.fetchSportsmans option').remove();

        $('.fetchSportsmans').next('.select2').find('.select2-selection__rendered').removeAttr('title').html('<span class="select2-selection__placeholder"></span>');
    });

    jQuery(document).ready(function($) {
        $(".clickable-row").click(function() {
            window.document.location = $(this).data("href");
        });
    });


    function addMessage(mainMessage, message, type) {
        $('#invite_button').after(
            "<div id='fail_invite' class='alert alert-" + type + " myWidth' role='alert'>" +
                "<a href='#' class='close' data-dismiss='alert' aria-label='close'>×</a>" +
                "<strong>" + mainMessage + "<br></strong> " + message +
            "</div>");
        $(".fetchSportsmans").text("");
    }


})(jQuery);




