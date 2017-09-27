import { YuitterClientPage } from './app.po';

describe('yuitter-client App', () => {
  let page: YuitterClientPage;

  beforeEach(() => {
    page = new YuitterClientPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
