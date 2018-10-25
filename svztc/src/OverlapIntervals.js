

function overlaps(list1, list2) {
    //@list1: array[interval], @list2: array[interval], @return: list[interval]
    i = 0, j = 0;
    a = [];
    while(i < list1.length && j < list2.length) {
        if(list1[i].start < list2[i].start) { //list1[i] comes first
            if(list1[i].end > list2[j].start) { //list1[i] overlaps with list2[j]
                a.push(list2[j].start, Math.min(list1[i].end, list2[j].end));
            }
        } else { //vice versa - list2[j] comes first
            if(list2[j].end > list1[i].start) {
                a.push(list1[j].start, Math.min(list1[i].end, list2[j].end));
            }
        }
        list1[i].end <= list2[j].end ? i++:j++;
    }
    return a;
}