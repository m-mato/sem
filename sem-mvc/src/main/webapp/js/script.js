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
    }

})(jQuery);
