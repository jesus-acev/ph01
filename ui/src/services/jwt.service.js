const ID_TOKEN_KEY = "user";

export const getToken = () => {
  var item = localStorage.getItem('user');
  if (item) {
    return JSON.parse(item).token;
  }
  else {
    return "";
  }
};

export const saveToken = token => {
  window.localStorage.setItem(ID_TOKEN_KEY, token);
};

export const destroyToken = () => {
  window.localStorage.removeItem(ID_TOKEN_KEY);
};

export default { getToken, saveToken, destroyToken };
