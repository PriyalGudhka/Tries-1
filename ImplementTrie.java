// Time Complexity : insert(): O(n), search(): O(n), startsWith(): O(n) where n is length of the word
// Space Complexity : insert(): O(1), search(): O(1), startsWith(): O(1). But overall space complexity will be O(nk) where n is the number of words and on an average each word is of length k
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes. Forgot about the root node

/*
Approach : Create a TrieNode class with isEnd & children[] variables where isEnd is used to mark the end of the word and children will carry the next letters of a word. Consider a root node of type TrieNode, for insert check if the children is not initialized, intialize it and move the curr node which starts from the root. Once the loop ends simply mark curr.isEnd = true. For search(), start from root node and iterate over the word to see if the each char exists and return curr.isEnd to ensure it was a complete word and not partial (Eg: We have a word apple, and expected to find app we must return false a p was not marked with isEnd as true). Similar logic for startsWith() but if all the children exists return true;
 */
class Trie {

    class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            curr = curr.children[c - 'a'];
        }

        curr.isEnd = true;
    }

    public boolean search(String word) {

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (curr.children[c - 'a'] == null) {
                return false;
            } else {
                curr = curr.children[c - 'a'];
            }

        }

        return curr.isEnd;
    }

    public boolean startsWith(String prefix) {

        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (curr.children[c - 'a'] == null) {
                return false;
            } else {
                curr = curr.children[c - 'a'];
            }

        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */