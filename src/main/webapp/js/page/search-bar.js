
function initSearchBar() {
    const searchBar = document.querySelector("input[data-search-bar]");
    if (searchBar) {
        const submit = searchBar.nextElementSibling;
        searchBar.addEventListener('keyup', (e) => {
            if (e.key === 'Enter') {
                launchSearch(searchBar.value);
            }
        });
        if (submit) {
            submit.addEventListener('click', () => {
                launchSearch(searchBar.value);
            });
        }
    }
}

function launchSearch(value) {
    if (value.trim()) {
        location.href = "/search/" + value;
    }
}

window.addEventListener('load', () => {
    initSearchBar();
});