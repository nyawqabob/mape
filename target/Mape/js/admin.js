function showBanForm() {
    var changeNameForm = document.getElementById("banForm");
    if (changeNameForm.style.display === "none") {
        changeNameForm.style.display = "block";
    } else {
        changeNameForm.style.display = "none";
    }
}

function show(p) {

    var modal = document.getElementById(p);
    modal.style.display = "block";
}
function hide(p) {
    var modal = document.getElementById(p);
    modal.style.display = "none";

}
function showUnbanForm() {
    var changeNameForm = document.getElementById("unbanForm");
    if (changeNameForm.style.display === "none") {
        changeNameForm.style.display = "block";
    } else {
        changeNameForm.style.display = "none";
    }
}
function showDeleteForm() {
    var changeNameForm = document.getElementById("deleteForm");
    if (changeNameForm.style.display === "none") {
        changeNameForm.style.display = "block";
    } else {
        changeNameForm.style.display = "none";
    }
}
function showAddForm() {
    var changeNameForm = document.getElementById("addForm");
    if (changeNameForm.style.display === "none") {
        changeNameForm.style.display = "block";
    } else {
        changeNameForm.style.display = "none";
    }
}
var $table = document.getElementById("users"),
        $n = 5,
        $rowCount = $table.rows.length,
        $firstRow = $table.rows[0].firstElementChild.tagName,
        $hasHead = ($firstRow === "TH"),
        $tr = [],
        $i, $ii, $j = ($hasHead) ? 1 : 0,
        $th = ($hasHead ? $table.rows[(0)].outerHTML : "");
var $pageCount = Math.ceil($rowCount / $n);
if ($pageCount > 1) {
    for ($i = $j, $ii = 0; $i < $rowCount; $i++, $ii++)
        $tr[$ii] = $table.rows[$i].outerHTML;
    $table.insertAdjacentHTML("afterend", "<div id='buttons'></div");
    sort(1);
}

function sort($p) {

    var $rows = $th, $s = (($n * $p) - $n);
    for ($i = $s; $i < ($s + $n) && $i < $tr.length; $i++)
        $rows += $tr[$i];
    $table.innerHTML = $rows;
    document.getElementById("buttons").innerHTML = pageButtons($pageCount, $p);
    document.getElementById("id" + $p).setAttribute("class", "active");
}



function pageButtons($pCount, $cur) {

    var $prevDis = ($cur == 1) ? "disabled" : "",
            $nextDis = ($cur == $pCount) ? "disabled" : "",
            $buttons = "<input type='button' value='&lt;&lt; Prev' onclick='sort(" + ($cur - 1) + ")' " + $prevDis + ">";
    for ($i = 1; $i <= $pCount; $i++)
        $buttons += "<input type='button' id='id" + $i + "'value='" + $i + "' onclick='sort(" + $i + ")'>";
    $buttons += "<input type='button' value='Next &gt;&gt;' onclick='sort(" + ($cur + 1) + ")' " + $nextDis + ">";
    return $buttons;
}






var $pubTable = document.getElementById("publication"),
        $pubn = 5,
        $pubRowCount = $pubTable.rows.length,
        $pubFirstRow = $pubTable.rows[0].firstElementChild.tagName,
        $pubHasHead = ($pubFirstRow === "TH"),
        $pubtr = [],
        $pubi, $pubii, $pubj = ($pubHasHead) ? 1 : 0,
        $pubth = ($pubHasHead ? $pubTable.rows[(0)].outerHTML : "");
var $pubPageCount = Math.ceil($pubRowCount / $pubn);
if ($pubPageCount > 1) {
    for ($pubi = $pubj, $pubii = 0; $pubi < $pubRowCount; $pubi++, $pubii++)
        $pubtr[$pubii] = $pubTable.rows[$pubi].outerHTML;
    $pubTable.insertAdjacentHTML("beforebegin", "<div id='pubbuttons'></div");

    pubsort(1);
}

function pubsort($p) {
    var $rows = $pubth, $s = (($pubn * $p) - $pubn);
    for ($pubi = $s; $pubi < ($s + $pubn) && $pubi < $pubtr.length; $pubi++)
        $rows += $pubtr[$pubi];

    $pubTable.innerHTML = $rows;

    document.getElementById("pubbuttons").innerHTML = pubPageButtons($pubPageCount, $p);

    document.getElementById("pubid" + $p).setAttribute("class", "active");
}

function pubPageButtons($pCount, $cur) {
    var $prevDis = ($cur == 1) ? "disabled" : "",
            $nextDis = ($cur == $pCount) ? "disabled" : "",
            $buttons = "<input type='button' value='&lt;&lt; Prev' onclick='pubsort(" + ($cur - 1) + ")' " + $prevDis + ">";
    for ($pubi = 1; $pubi <= $pCount; $pubi++)
        $buttons += "<input type='button' id='pubid" + $pubi + "'value='" + $pubi + "' onclick='pubsort(" + $pubi + ")'>";
    $buttons += "<input type='button' value='Next &gt;&gt;' onclick='pubsort(" + ($cur + 1) + ")' " + $nextDis + ">";
    return $buttons;
}
