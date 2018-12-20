var NamesRegistry = artifacts.require('NamestRegistry.sol');

module.exports = function(deployer) {
  deployer.deploy(NamesRegistry);
};
