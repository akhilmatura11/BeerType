# BeerType
API URL: https://api.punkapi.com/v2/beers?page=1&per_page=20 Language: Kotlin
(Tab1 / Tab2 / Detail page)
Create a simple Kotlin app.
1. Show the items on the list, using the api above.
2. Themainscreenhastwotabs.Onthefirsttap,itemsdisplayinformationinthe
following order: Checkbox, Image, Name. On the second tap,items display
information following order: Checkbox, Name, Image.
3. Implement pagination. When the user scrolls down, the page should be
automatically loaded.
4. If the user clicks the item, show the detail page.
5. Implementthecheckbox.
  a. b. c.
If the user checked an item from tab1, then tab2 and the detail page need to show check status also.
If the user checked an item from tab2, then tab1 and the detail page need to show check status also.
If the user checked from the detail page, then tab1 and tab2 must show the check status also.
6. Checkstatusmustbekeptwhenthescreenorientationischanged.
