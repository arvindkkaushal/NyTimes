@RunWith(AndroidJUnit4::class)
class ResultActivityEspressoTest {

    @Before
    fun launchActivity() {
        ActivityScenario.launch(ResultActivity::class.java)
    }

    @JvmField
    @Rule
    val testRule = ActivityTestRule<ResultActivity>(ResultActivity::class.java)

    @Test
    fun initialViewsDisplayedProperly() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.recyelerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewClick(){
        // Click item at position 3
        onView(withId(R.id.recyelerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(ViewMatchers.withId(R.id.recyelerView))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))

        // Check that the item has the special text.
        val middleElementText = getApplicationContext<Context>().resources.getString(R.string.middle)
        onView(ViewMatchers.withText(middleElementText)).check(matches(isDisplayed()))
    }

    /**
     * Matches the [ResultAdapter.ViewHolder]s in the middle of the list.
     */
    private fun isInTheMiddle(): Matcher<ResultAdapter.ViewHolder> {
        return object : TypeSafeMatcher<ResultAdapter.ViewHolder>() {
            override fun matchesSafely(customHolder: ResultAdapter.ViewHolder): Boolean {
                return customHolder.isInTheMiddle
            }

            override fun describeTo(description: Description) {
                description.appendText("item in the middle")
            }
        }
    }

}