pragma solidity ^0.4.24;

contract NamesRegistry {

    struct Name {
        string firstName;
        string surname;
    }

    Name[] names;

    function addName(string firstName, string surname) external {
        names.push(Name(firstName, surname));

        emit NameAdded(names.length - 1, firstName, surname);
    }

    event NameAdded(uint256 id, string firstName, string surname);
}