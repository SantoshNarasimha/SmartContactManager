console.log("This is script file");

document.addEventListener("DOMContentLoaded", function () {

    const alertElement = document.getElementById("successAlert");

    if (alertElement) {
        setTimeout(function () {
            const bootstrapAlert = new bootstrap.Alert(alertElement);
            bootstrapAlert.close();
        }, 4000); // 4 seconds
    }

});


const toggleSidebar = () => {

    if ($(".sidebar").is(":visible")) {
        //true 
		//band karna hai
		$(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    } else {
        // false
		// show karna hai
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};
const search = () => {
    let query = $("#search-input").val();

    if (query === "") {
        $(".search-result").hide();
    } else {
        let url = `http://localhost:8484/search/${query}`;

        fetch(url)
            .then((response) => response.json())
            .then((data) => {
                console.log(data);

                let text = `<div class="list-group">`;

                data.forEach((contact) => {
                    text += `<a href="/user/show_details/${contact.cid}" 
                             class="list-group-item list-group-item-action">
                                ${contact.name}</a>`;
                });

                text += `</div>`;

                // 1. Actually inject the generated HTML into the page
                $(".search-result").html(text);

                // 2. Show the results container ONLY after it has the data
                $(".search-result").show();

            })

            .catch((error) => {

                // Always good practice to catch network errors!
                console.error("Error fetching contacts:", error);

            });
    }
}